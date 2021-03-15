package com.umpay.rms.gpd.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public interface TemplateIdService {

	String newTemplateId( );

	void saveTemplateReference( String platTemplateId, String gwId, String gwTemplateId );

	String loadTemplateReference( String platTemplateId, String gwId );

	public static final class DelegateTemplateIdService {

		@Autowired
		private TemplateIdService delegate;

		private static final DelegateTemplateIdService INSTANCE = new DelegateTemplateIdService( );

		public static DelegateTemplateIdService getInstance( ) {
			return INSTANCE;
		}

		private DelegateTemplateIdService( ) {}

		public TemplateIdService getDelegate( ) {
			return delegate;
		}

		public void setDelegate( TemplateIdService delegate ) {
			this.delegate = delegate;
		}

		public String newTemplateId( ) {
			return delegate.newTemplateId( );
		}

		public void saveTemplateReference( String platTemplateId, String gwId, String gwTemplateId ) {
			delegate.saveTemplateReference( platTemplateId, gwId, gwTemplateId );
		}

		public String loadTemplateReference( String platTemplateId, String gwId ) {
			return delegate.loadTemplateReference( platTemplateId, gwId );
		}

	}
}
