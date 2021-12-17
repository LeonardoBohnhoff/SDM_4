package org.psjava.algo;

import org.psjava.ds.array.PSArray;
import org.psjava.ds.array.UniformArray;
import org.psjava.util.*;

import ch.usi.si.codelounge.jsicko.Contract;
import ch.usi.si.codelounge.jsicko.Contract.Requires;

import java.util.function.Predicate;

public class KSizeSubSetIterable implements Contract{

	@Pure
	public static <T> boolean k_must_be_less_or_equal_than_superset_size(final PSArray<T> superSet, int k) {
		return k <= superSet.size();
	}

	@Requires("k_must_be_less_or_equal_than_superset_size")
    public static <T> Iterable<Iterable<T>> create(final PSArray<T> superSet, int k) {
        // Assertion.ensure(k <= superSet.size());
        PSArray<Boolean> trues = UniformArray.create(true, k);
        PSArray<Boolean> falses = UniformArray.create(false, superSet.size() - k);
        Iterable<Boolean> flags = MergedIterable.wrap(VarargsIterable.create(trues, falses));
        Iterable<PSArray<Boolean>> permutations = PermutationWithRepetitionIterable.create(flags, ReversedComparator.wrap(new DefaultComparator<Boolean>()));
        return ConvertedIterable.create(permutations, new Converter<PSArray<Boolean>, Iterable<T>>() {
            @Override
            public Iterable<T> convert(final PSArray<Boolean> permutation) {
                return ConvertedIterable.create(FilteredIterable.create(ZeroTo.get(permutation.size()), new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer index) {
                        return permutation.get(index);
                    }
                }), new Converter<Integer, T>() {
                    @Override
                    public T convert(Integer index) {
                        return superSet.get(index);
                    }
                });
            }
        });
    }

}
