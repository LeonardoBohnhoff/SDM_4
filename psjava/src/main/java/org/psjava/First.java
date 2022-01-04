package org.psjava;

import java.util.List;

import ch.usi.si.codelounge.jsicko.Contract;

public class First implements Contract{

	@Pure
	private static <T> boolean non_empty_list(List<T> arg) {
		return arg.size() > 0;
	}
	
	@Pure
    private static <T> boolean non_empty_result(T returns, List<T> arg) {
        return returns != null;
    }

	@Requires("non_empty_list")
	@Ensures("non_empty_result")
    public static <T> T first(List<T> a) {
        return a.get(0);
    }
}
