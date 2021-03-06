package com.inf191.lachesis.generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * fitbitdata
 * @author 
 */
@Data
@Setter
@Getter
public class Fitbitdata implements Serializable {
    private Integer heardataid;

    private Integer fid;

    private Integer heartrate;

    private Date time;

    private static final long serialVersionUID = 1L;
}