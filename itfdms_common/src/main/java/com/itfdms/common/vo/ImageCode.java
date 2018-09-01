package com.itfdms.common.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lxr
 * @Title: ImageCode
 * @ProjectName itfdms_blog
 * @Description: TODO
 * @date 2018-07-0716:14
 */

@Data
public class ImageCode implements Serializable {

    private static final long serialVersionUID = -3417248863570623053L;


    private String code;

    private LocalDateTime expireTime;

    private BufferedImage image;

    public ImageCode(BufferedImage image, String sRand, int defaultImageExpire){
        this.image = image;
        this.code = sRand;
        this.expireTime = LocalDateTime.now().plusSeconds(defaultImageExpire);

    }
}
