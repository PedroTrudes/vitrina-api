package br.com.vitrina_api.shared.exception;

public enum ErrorCode {
    //Invites
    INVITE_NOT_FOUND,
    INVITE_EXPIRED,
    INVITE_INVALID,

    //Auth
    INVALID_CREDENTIALS,
    UNAUTHORIZED,

    VALIDATION_ERROR,
    //Generico
    INTERNAL_ERROR
}
