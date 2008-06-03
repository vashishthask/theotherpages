package net.sf.theotherpages.data;

/**
 * TODO A summary sentence containing a concise but complete description of the
 * API item
 * <p>
 * <b>Overview: </b>
 * <p>
 * TODO Mention the overview of class using <b>,
 * <p>,
 * <li>,<ui>,<code></code>,
 * 
 * <pre></pre>,<i></i>
 * 
 * <p>
 * <DL>
 * <DT><B>History: </B>
 * <DD>Oct 10, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @version 1.0, Oct 10, 2007
 * 
 * @since v1.0, Oct 10, 2007
 * 
 */
public class PaginationCallbackParams {
	/**
	 * TODO Comment for <code>queryParams</code>
	 */
	Object queryParams;
	/**
	 * TODO Comment for <code>otherParams</code>
	 */
	Object[] otherParams;

	/**
	 * Creates a PaginationCallbackParams with TODO Name of types separated with
	 * comma
	 * <p>
	 * @param queryParams 
	 * @param otherParams 
	 * 
	 */
	public PaginationCallbackParams(Object queryParams, Object[] otherParams
			) {
		this.queryParams = queryParams;
		this.otherParams = otherParams;
	}

	/**
	 * Creates a PaginationCallbackParams with TODO Name of types separated with
	 * comma
	 * <p>
	 * @param queryParams 
	 * 
	 */
	public PaginationCallbackParams(Object queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * Returns the queryParams
	 * 
	 * @return Object
	 */
	public Object getQueryParams() {
		return queryParams;
	}

	/**
	 * Returns the otherParams
	 * 
	 * @return Object[]
	 */
	public Object[] getOtherParams() {
		return otherParams;
	}
}