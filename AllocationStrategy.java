package main;

import java.util.Comparator;

public interface AllocationStrategy {
    Comparator<Warehouse> getComparator(String productId);
}