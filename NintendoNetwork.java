/**
 * NintendoNetwork class is main class of tree.  
 * 
 *  @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.io.*;
import java.util.*;

public class NintendoNetwork {
	public static void main(String[]args) {
		NetworkTree tree= new NetworkTree();
		NetworkNode node= new NetworkNode();
		NetworkNode paste= new NetworkNode();
		boolean quit=true;
		boolean cut=false;
		Scanner sc= new Scanner(System.in);
		System.out.println("Welcome to the Nitendo Network Manager\n");
		System.out.println("Menu:\n\tL) Load from file\n\tP) Print tree"+
				"\n\tC) Move cursor to a child node\n\tR) Move cursor to root"+
				"\n\tU) Move cursor up to parent\n\tA) Add a child\n\tX) Remove/Cut Cursor and its subtree"+
				"\n\tV) Paste Cursor and its subtree\n\tS) Save to file\n\tM) Cursor to root of minimal subtree containing all faults"+
				"\n\tB) Mark cursor as broken/fixed\n\tQ) Quit\n");
		while(quit) {
		System.out.print("Please select an option: ");
		char choice= sc.nextLine().charAt(0);
		//Load from file
			if(choice=='L'|choice=='l') {
				System.out.print("Please enter filename: ");
				String fname= sc.nextLine();
				try {			
				tree=NetworkTree.readFromFile(fname);
				}catch(FileNotFoundException e){
					System.out.println(fname+" not found\n");
				}	
			}
			//Print
			if(choice=='P'|choice=='p') {
				tree.printString();
			}
			//Cursor to child (index number)
			if(choice=='C'|choice=='c') {
				System.out.print("Please enter an index: ");
				int index = Integer.parseInt(sc.nextLine());
				tree.cursorToChild(index-1);
				System.out.println("Cursor moved to "+tree.getCursor().getName()+".\n");
			}
			//Add child (index, type, prompt for text)
			if(choice=='A'|choice=='a') {
				try {
					System.out.print("Please enter an index: ");
					int index = Integer.parseInt(sc.nextLine());
					System.out.print("Please enter device name: ");
					String node1 = sc.nextLine();
					System.out.print("Is this Nitendo (y/n): ");
					char letter = sc.nextLine().charAt(0);
					NetworkNode dummy= new NetworkNode(node1);
					tree.addChild(index, dummy);
					if(letter=='Y'|letter=='y') {
						dummy.setIsNitendo(true);
						System.out.println("Nitendo added\n");
					}else {
						System.out.println("Node added");
					}
				}catch(HoleException | FullException e) {
					System.out.println("Index invalid!\n");
				}

			}
			//Cursor up (to parent)
			if(choice=='U'|choice=='u') {
				tree.cursorToParent();
				System.out.println("Cursor moved to "+tree.getCursor().getName()+".\n");
			}
			//Cut/Delete cursor
			if(choice=='X'|choice=='x') {
				paste= tree.cutCursor();
				cut=true;
				
			}
			//Paste Subtree (index number)
			if(choice=='V'|choice=='v') {
				if(cut==false) {
					System.out.println("There is nothing to paste!\n");
				}else {
					try {
						System.out.print("Please enter an index: ");
						int index=Integer.parseInt(sc.nextLine());
						tree.addChild(index, paste);
						System.out.println(paste.getName()+" pasted as a child of "+tree.getCursor().getName());
					}catch(HoleException | FullException e) {
						System.out.println("Index invalid!\n");
					}
				}
				
			}
			//Cursor to root
			if(choice=='R'|choice=='r') {
				tree.cursorToRoot();
				System.out.println("Cursor moved to "+tree.getCursor().getName()+".\n");
			}
			//Save to Text File
			if(choice=='S'|choice=='s') {
				System.out.print("Please enter a filename: ");
				String filename=sc.nextLine();
				try {
					tree.writeToFile(tree, filename);
				}catch(FileNotFoundException e) {
					System.out.println(filename +" could not be found!\n");
				}
				
			}
			//Extra credit
			if(choice=='M'|choice=='m') {
				System.out.println("Function does not work.");
			}
			//Extra credit
			if(choice=='B'|choice=='b') {
				System.out.println("Function does not work.");
			}
			//Quit
			if(choice=='Q'|choice=='q') {
				System.out.println("Make like a tree and leave!");
				quit=false;
			}
		}
	
	}
}
