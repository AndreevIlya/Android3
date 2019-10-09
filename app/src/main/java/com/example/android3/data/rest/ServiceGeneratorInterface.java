package com.example.android3.data.rest;

public interface ServiceGeneratorInterface<S>  {
    S createService(Class<S> serviceClass);
}
