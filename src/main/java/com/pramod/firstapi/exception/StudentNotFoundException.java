package com.pramod.firstapi.exception;

public class StudentNotFoundException
extends RuntimeException {

public StudentNotFoundException(
    String message) {

super(message);
}
}