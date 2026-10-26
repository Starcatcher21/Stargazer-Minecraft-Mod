//? if <= 1.21.4 {
package com.github.starcatcher21.stargazer.compat;

import java.util.function.Supplier;

/**
 * Minimal stand-in for Architectury's RegistrySupplier on 1.21.1.
 * Holds the registered value and exposes it via Supplier#get().
 */
public final class SimpleRegistrySupplier<T> implements Supplier<T> {
    private final T value;

    public SimpleRegistrySupplier(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }
}
//? }
