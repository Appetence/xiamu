package com.umpay.rms.gpd.internal.util;

import javax.annotation.PostConstruct;
import java.util.regex.Pattern;

public class MobileSection {

	private String regex= "^\\+?(\\(?0{0,2}(86)?\\)?)1(((\\d{2}|(44)\\d{2}|(65)\\d{2}|(0648)|(0647))\\d{8}))$";

	private Pattern pattern;

	@PostConstruct
	public void init(){
		pattern = Pattern.compile( regex );
	}

	public MobileSection( ) {}

	public void update( String regex ) {
		this.regex = regex;
		this.pattern = Pattern.compile( regex );
	}

	public boolean isMobile( String mobile ) {
		return pattern.matcher( mobile ).matches( );
	}

}
