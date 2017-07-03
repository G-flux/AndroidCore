package com.g_flux.androidcore.utils;

import com.g_flux.androidcore.interfaces.Transformer;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public class IdentityTransformer<E> implements Transformer<E, E> {
    @Override
    public E transform(E item) {
        return item;
    }
}
