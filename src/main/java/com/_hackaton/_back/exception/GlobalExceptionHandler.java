package com._hackaton._back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //빈 이미지 리스트가 반환 되었을 때 발생한 예외 처리
    @ExceptionHandler(EmptyImageListException.class)
    public ResponseEntity<String> handleEmptyImageListException(EmptyImageListException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //파일 클라우드에 업로드 중 IOException 발생 시 에외 처리
    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<String> handleImageUploadException(ImageUploadException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }


}
