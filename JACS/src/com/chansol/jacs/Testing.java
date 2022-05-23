package com.chansol.jacs;

import com.chansol.jacs.nodes.ContractDefinition;
import com.chansol.jacs.nodes.Node;

public class Testing {

	public static void main(String[] args) {
		JACS jacs = new JACS("source/BelpGaming.sol", true);
		for(Node contractDefition : jacs.getAll("BelpGaming","ContractDefinition")){
			System.out.println(((ContractDefinition)contractDefition).getName());
			for(long id : contractDefition.getParentTrees()) {
				System.out.print(id+" ");
			}
			System.out.println();
		}
		for(long id : jacs.searchByID("BelpGaming", 687).getParentTrees()) {
			System.out.print(id+" ");
		}
//		for(String failed : jacs.getFailedList()) {
//			System.out.println(failed);
//		}
	}

}