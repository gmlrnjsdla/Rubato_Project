package com.rubato.homepage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RFBoardDto {

	private int rfbnum;
	private String rfbname;
	private String rfbtitle;
	private String rfbcontent;
	private int rfbhit;
	private String rfbuserid;
	private String rfbreplycount;
	private String rfbdate;
	private int filecount;
}
