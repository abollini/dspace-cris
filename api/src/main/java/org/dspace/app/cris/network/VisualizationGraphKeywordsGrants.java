package org.dspace.app.cris.network;


public class VisualizationGraphKeywordsGrants extends AVisualizationGraphModeTwo  {

	public final static String CONNECTION_NAME = "keywordsgrants";
	private static final String FIELD_QUERY = "network.pjkeywords_keyword";
	

	@Override
	public String getType() {
		return CONNECTION_NAME;
	}

	@Override
	public String getLineWidthToOverride() {
		return "1.5";
	}

	

	@Override
	public String getConnectionName() {
		return CONNECTION_NAME;
	}
	
	@Override
	protected String getFacetFieldQuery() {
		return FIELD_QUERY;
	}

	@Override
	public String getFacet(String value) {
		return FA_VALUE;
	}
    @Override
    protected String buildExtraCustom(String extra)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
