package br.com.vitrina_api.shared.exception;

public enum ErrorCode {
    //User
    EMAIL_ALREADY_EXISTS,
    USER_NOT_FOUND,

    //Invites
    INVITE_NOT_FOUND,
    INVITE_EXPIRED,
    INVITE_INVALID,

    //Auth
    INVALID_CREDENTIALS,
    UNAUTHORIZED,

    VALIDATION_ERROR,

    //Store
    STORE_NOT_FOUND,

    //ContactStore
    CONTACT_NOT_FOUND,
    CONTACT_INVALID,

    //Generico
    INTERNAL_ERROR
}
