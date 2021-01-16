/**
 * NetworkNode class is used to hold node of tree.  
 * 
 *  @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.io.*;
import java.util.*;

public class NetworkNode {
	private String name;
	private boolean isNintendo;
	private boolean isBroken;
	private NetworkNode parent;
	private NetworkNode[] children;  
	final int maxChildren=9;
/**
 * default constructor of NetworkNode
 */
	public NetworkNode() {
		this.name=null;
		this.isNintendo=false;
		this.isBroken=false;
		this.parent=null;
		this.children= new NetworkNode[maxChildren];
	}
	/**
	 * creates node with name from string node 
	 * @param node
	 */
	public NetworkNode(String node) {
		this.name=node;
		this.isNintendo=false;
		this.isBroken=false;
		this.parent=null;
		this.children= new NetworkNode[maxChildren];
	}
	/**
	 * returns name of node
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * returns true if node is nitendo else return false
	 * @return
	 */
	public boolean getIsNitendo() {
		return this.isNintendo;
	}
	/**
	 * returns true if node is broken else return false
	 * @return
	 */
	public boolean getIsBroken() {
		return this.isBroken;
	}
	/**
	 * returns parent of node 
	 * @return
	 */
	public NetworkNode getParent() {
		return this.parent;
	}
	/**
	 * returns children of node
	 * @return
	 */
	public NetworkNode[] getChildren() {
		return this.children;
	}
	/**
	 * sets name of node
	 * @param name
	 */
	public void setName(String name) {
		this.name=name;
	}
	/**
	 * sets true or false of isNitendo for node
	 * @param bool
	 */
	public void setIsNitendo(boolean bool) {
		this.isNintendo=bool;
	}
	/**
	 * sets true or false of isBroken for node
	 * @param bool
	 */
	public void setIsBroken(boolean bool) {
		this.isBroken=bool;
	}
	/**
	 * sets parent of node
	 * @param parent
	 */
	public void setParent(NetworkNode parent) {
		this.parent=parent;
	}
	/**
	 * sets children of node
	 * @param children
	 */
	public void setChildren(NetworkNode[] children) {
		this.children=children;
	}
	public boolean hasChildren() {
		for(int i=0;i<this.children.length;i++) {
			if(children[i]!=null) {
				return false;
			}
		}
		return true;
	}
	public int childrenLength() {
		for(int i=0;i<this.children.length;i++) {
			if(children[i]==null) {
				return i;
			}
		}
		return children.length;
	}
	/**
	 * returns parameters of node
	 */
	public String toString() {
		return "name: "+this.name+" Is nitendo: "+this.isNintendo+" Is broken: "+this.isBroken;
	}
}
