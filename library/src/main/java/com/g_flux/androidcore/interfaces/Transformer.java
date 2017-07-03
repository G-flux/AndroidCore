package com.g_flux.androidcore.interfaces;

/**
 * @author Kenneth Saey
 * @company G-flux
 * @since 03-07-2017 14:35
 */
public interface Transformer<E, R> {

    R transform(E item);
}
