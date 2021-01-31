package com.kindee.proxyapi.spider;

import java.util.Collection;
import java.util.LinkedList;

public class Schedule<T> {

	private LinkedList<T> linkList = new LinkedList<T>();

	/**
	 * add one url to the schedule
	 * @param t
	 */
	public synchronized  void put(T t){
		if (linkList.contains(t))
			return;
		linkList.addLast(t);
	}


	/**
	 * put url to the schedule using a collection
	 * @param coll
	 */
	public synchronized void putALL(Collection<T> coll){
		
		for(T t : coll){
			if (linkList.contains(t))
				return;
			linkList.addLast(t);
		}
		
	}

	/**
	 * get the first url
	 * @return
	 */
	public synchronized T get(){
		if(linkList.size() == 0)
			return null;
		return linkList.removeFirst();

	}

	public void removeAll(){
		linkList.removeAll(linkList);
	}

	/**
	 * get the left counts of the schdule
	 * @return
	 */
	public int size(){
		return linkList.size();
	}

	/**
	 * check if the schedule has url left
	 * @return
	 */
	public boolean hasNext(){
		return linkList.size() > 0;
	}

}
