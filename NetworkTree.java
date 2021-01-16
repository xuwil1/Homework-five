/**
 * NetworkTree class is tree manager. It holds nodes of NetworkNode. 
 * 
 *  @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.io.*;
import java.util.*;

public class NetworkTree {
	private NetworkNode root;
	private NetworkNode cursor;
	/**
	 * Sets the cursor to the root of the NetworkTree
	 */
	public void cursorToRoot() {
		this.cursor=this.root;
	}
	/**
	 * returns node that is being cut
	 * @return
	 */
	public NetworkNode cutCursor() {
		if(cursor!=null) {
		NetworkNode temp=this.cursor;
		cursorToParent();
		NetworkNode[] shiftedArray= shiftOver(this.cursor.getChildren(),temp,cursor.childrenLength());
		temp.setParent(null);
		cursor.setChildren(shiftedArray);
		System.out.println(temp.getName()+" cut, cursor is at "+ this.cursor.getName());
		return temp;
		}else {
			return null;
		}
	}
	public NetworkNode[] shiftOver(NetworkNode[] nodes,NetworkNode temp,int length) {
		for(int i=0;i<length;i++) {
			if(nodes[i].getName().equals(temp.getName())) {
				for(int j=i;j<(8);j++) {
					nodes[j]=nodes[j+1];
				}
				nodes[8]=null;
			}
		}
		return nodes;
	}

	/**
	 * 
	 * @param index
	 * @param node
	 * Exception is thrown if adding the node at the specified index makes a hole in the array
	 * @throws HoleException
	 */
	public void addChild(int index, NetworkNode node) throws HoleException, FullException {
		if(index>9) {
			throw new FullException();
		}
		if(this.root==null) {
			this.setRoot(node);
			this.cursor=this.root;
		}else {
			for(int i=0;i<index-1;i++) {
				if(this.cursor.getChildren()[i]==null) {
					throw new HoleException();
				}
			}
			this.cursor.getChildren()[index-1]=node;
			node.setParent(getCursor());
			this.cursor=node;
		}
			
	}
	/**
	 * Moves the cursor to the child node of the of the cursor corresponding to the specified index.
	 * user specified index
	 * @param index
	 */
	public void cursorToChild(int index) {
		this.cursor=cursor.getChildren()[index];
	}
	/**
	 * Moves the cursor to the parent of the current node.
	 */
	public void cursorToParent() {
		this.cursor=cursor.getParent();
	}
	/**
	 * user specified file name
	 * @param filename
	 * returns file scanned in a tree
	 * @return
	 */
	public static NetworkTree readFromFile(String filename) throws FileNotFoundException{
		File file = new File(filename);
		NetworkTree tree = new NetworkTree();
		NetworkNode dummy;
		Scanner scan = new Scanner(file);
		NetworkNode tempRoot = new NetworkNode(scan.nextLine());
		tree.setRoot(tempRoot);
		while (scan.hasNextLine()) {
			tree.cursorToRoot();
			String numbers = "";
			String name = "";
			String currentLine = scan.nextLine();
			char[] charArray = currentLine.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				if (Character.isDigit(charArray[i])) {
					numbers += Character.toString(charArray[i]);
				} else {
					name += Character.toString(charArray[i]);
				}
			}
			char[] number = numbers.toCharArray();
			for (int i = 0; i < number.length - 1; i++) {
				int num=Character.getNumericValue(number[i]);
				tree.cursorToChild(num-1);		//convert to integer
			}
			
			if(name.substring(0,1).equals("-")) {
			name=name.substring(1);
			dummy = new NetworkNode(name);
			dummy.setIsNitendo(true);
			}else {
			dummy = new NetworkNode(name);
			}
			try {
				int ind=Character.getNumericValue(number[number.length-1]);
				tree.addChild(ind, dummy);
			}catch(HoleException | FullException e) {	
			}	
		}
		System.out.println(filename + " loaded");
		scan.close();
		tree.cursorToRoot();
		return tree;
	}
	/**
	 * writes tree to file 
	 * the tree that is written to file
	 * @param tree
	 * name that file is written to
	 * @param filename
	 * throws FileNotFoundException if file is not found.
	 * @throws FileNotFoundException
	 */
	public static void writeToFile(NetworkTree tree, String filename) throws FileNotFoundException {
		String s="";
		String str="";
		PrintWriter writer= new PrintWriter(filename);
		str=tree.saveTree(tree.getRoot(),"",s);
		System.out.println(str);
		writer.print(str);
		writer.flush();
		writer.close();
		System.out.println("File saved.");
	}
	/**
	 * sets root of tree
	 * @param root
	 */
	public void setRoot(NetworkNode root) {
		this.root=root;
	}
	/**
	 * returns root of tree
	 * @return
	 */
	public NetworkNode getRoot() {
		return this.root;
	}
	/**
	 * returns cursor of tree
	 * @return
	 */
	public NetworkNode getCursor() {
		return this.cursor;
	}
	/**
	 * prints string for network tree
	 */
	public void printString() {
		printTree(getRoot(),0);
	}
	/**
	 * helper method for printString
	 * takes in root node
	 * @param node
	 * starts at 0 and determines spacing 
	 * @param tab
	 */
	public void printTree(NetworkNode node,int tab) {
		for(int j=0;j<tab;j++) {
			System.out.print("\t");
		}
		if(this.cursor.equals(node)) {
			System.out.print("->");
		}
		else if(node.getIsNitendo()==false) {
			System.out.print("+");
		}else {
			System.out.print("-");
		}
		if(node.hasChildren()!=false) {
			System.out.println(node.getName());
		}else {
			System.out.println(node.getName());
			for(int i=0;i<node.childrenLength();i++) {
				if(node.getChildren()[i]!=null) {
					printTree(node.getChildren()[i],tab+1);
				}
				}	
		}
	}
	/**
	 * helper method for save file
	 * takes in node 
	 * @param node
	 * num is string that prints the location of the node
	 * @param num
	 * String s is a long string that is returned for printer write to write to file
	 * @param s
	 * returns s
	 * @return
	 */
	public String saveTree(NetworkNode node,String num,String s){

		if(node.hasChildren()!=false) {
			if(num!="") {
				if(node.getIsNitendo()) {
					return num +"-"+node.getName()+"\n";
				}else {
					return num+node.getName()+"\n";
				}
			}else{
				if(node.getIsNitendo()) {
					return "-"+node.getName()+"\n";
				}else {
					return node.getName()+"\n";
				}
			}
		}else {
			
			if(num!="") {
				if(node.getIsNitendo()) {
				s= num + "-"+node.getName()+"\n";
				}else {
				s= num + node.getName()+"\n";
				}
			}else {
				if(node.getIsNitendo()) {
					s= "-"+ node.getName()+"\n";
				}else {
					s=node.getName()+"\n";
				}
			}
			for(int i=0;i<node.childrenLength();i++) {
				if(node.getChildren()[i]!=null) {
					s+=saveTree(node.getChildren()[i],num+(i+1),s);
				}
			}
		}
		System.out.println(s);
		return s;

	}
}
