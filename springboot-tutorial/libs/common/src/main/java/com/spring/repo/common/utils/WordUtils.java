package com.spring.repo.common.utils;

import lombok.experimental.UtilityClass;

import java.text.Normalizer;
import java.util.Objects;

@UtilityClass
public class WordUtils {
    public static String toNonVietnamese(String vnWord) {
        if (Objects.isNull(vnWord)) {
            return null;
        } else {
            vnWord = Normalizer.normalize(vnWord, Normalizer.Form.NFC);
            vnWord = vnWord.replaceAll("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
            vnWord = vnWord.replaceAll("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
            vnWord = vnWord.replaceAll("ì|í|ị|ỉ|ĩ", "i");
            vnWord = vnWord.replaceAll("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
            vnWord = vnWord.replaceAll("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
            vnWord = vnWord.replaceAll("ỳ|ý|ỵ|ỷ|ỹ", "y");
            vnWord = vnWord.replaceAll("đ", "d");
            vnWord = vnWord.replaceAll("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A");
            vnWord = vnWord.replaceAll("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E");
            vnWord = vnWord.replaceAll("Ì|Í|Ị|Ỉ|Ĩ", "I");
            vnWord = vnWord.replaceAll("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O");
            vnWord = vnWord.replaceAll("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U");
            vnWord = vnWord.replaceAll("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y");
            vnWord = vnWord.replaceAll("Đ", "D");
            return vnWord;
        }
    }
}
