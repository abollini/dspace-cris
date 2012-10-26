<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.dspace.org/dspace-tags.tld" prefix="dspace" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="it.cilea.hku.authority.dspace.HKUAuthority"%>
<%@ page import="java.net.URL"%>
<%@ page import="it.cilea.hku.authority.util.ResearcherPageUtils"%>
<%@ page import="java.io.File"%>
<%@ page import="org.dspace.core.ConfigurationManager"%>
<%@ page import="org.dspace.browse.BrowseInfo"%>
<%@ page import="org.dspace.app.webui.util.UIUtil" %>

<%@ taglib uri="jdynatags" prefix="dyna"%>
<%@ taglib uri="researchertags" prefix="researcher"%>
<%@ page import="it.cilea.hku.authority.util.ResearcherPageUtils"%>
<c:set var="root"><%=request.getContextPath()%></c:set>
<c:set var="entity" value="${project}" scope="request" />


<%
	// Is the logged in user an admin
	Boolean admin = (Boolean)request.getAttribute("is.admin");
	boolean isAdmin = (admin == null ? false : admin.booleanValue());
    // Get the current page, minus query string
    String currentPage = UIUtil.getOriginalURL(request);
    int c = currentPage.indexOf( '?' );
    if( c > -1 )
    {
        currentPage = currentPage.substring( 0, c );
    }

%>
<c:set var="admin" scope="request"><%= isAdmin %></c:set>
<c:set var="dspace.cris.navbar" scope="request">


<c:if test="${grant_page_menu && !empty project}">


  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  
  <tr>
    <td nowrap="nowrap" colspan="2" class="navigationBarSublabel"><fmt:message key="jsp.layout.navbar-hku.staffmode.title"/></td>
  </tr>
  

  <c:if test="${!empty addModeType && addModeType=='display'}">
  
  <tr class="navigationBarItem">
    <td>
      <img alt="" src="<%= request.getContextPath() %>/image/<%= ( currentPage.endsWith( "/editDynamicData" ) ? "arrow-highlight" : "arrow" ) %>.gif" width="16" height="16"/>
    </td>
    <td nowrap="nowrap" class="navigationBarItem">
      <a href="<%= request.getContextPath() %>/cris/tools/project/editDynamicData.htm?id=${project.id}&anagraficaId=${project.dynamicField.id}<c:if test='${!empty tabIdForRedirect}'>&tabId=${tabIdForRedirect}</c:if>"><fmt:message key="jsp.layout.navbar.entity.edit"/></a>
    </td>
  </tr>  
 </c:if>
  
   <tr class="navigationBarItem">
    <td>
      <img alt="" src="<%= request.getContextPath() %>/image/<%= ( currentPage.endsWith( "/help#Projects" ) ? "arrow-highlight" : "arrow" ) %>.gif" width="16" height="16"/>
    </td>
    <td nowrap="nowrap" class="navigationBarItem">
      <a href="<%= request.getContextPath() %>/help.jsp#Projects">Help</a>
    </td>
  </tr>
 
 </c:if>


<c:if test="${!empty entity && (!empty addModeType && addModeType=='display')}">    
 <% if (!isAdmin) { %>
 <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
<% } %>	
 <tr> 
  <td colspan="2">
		
	    <c:forEach items="${tabList}" var="tabfornavigation">				
			
				<div id="cris-tabs-navigation-${tabfornavigation.shortName}" class="navigation-tabs" style="display: none">		

					<div id="menu-${tabfornavigation.shortName}" class="showMoreLessBox1-dark box">
						<h3 class="showMoreLessControlElement control ${tabfornavigation.id != tabId?"":"expanded"}">
						<img src="<%=request.getContextPath() %>/image/cris/btn_lite_expand.gif"  ${tabfornavigation.id != tabId?"":"class=\"hide\""}/>
						<img src="<%=request.getContextPath() %>/image/cris/btn_lite_collapse.gif" ${tabfornavigation.id != tabId?"class=\"hide\"":""} />
							${tabfornavigation.title}
						</h3>		
						<div class="collapsable expanded-content" ${tabfornavigation.id != tabId?"style=\"display: none;\"":""}>
						<div id="nav-sublocal">
						<ul>
						<div id="snavmenu-${tabfornavigation.shortName}">
							<div class="log">
							<img
								src="<%=request.getContextPath()%>/image/jdyna/indicator.gif"
			    				class="loader" />
			    			</div>
						</div>
						</ul>
						</div>
						</div>
					</div>
		

				</div>
			
		 </c:forEach>
	   
	</td>
  </tr> 
</c:if>
 

</c:set>

<c:set var="dspace.layout.head" scope="request">
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.24.custom.min.js"></script>
    <link href="<%= request.getContextPath() %>/css/researcher.css" type="text/css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/css/jdyna.css" type="text/css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/css/redmond/jquery-ui-1.8.24.custom.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript"><!--

		var j = jQuery.noConflict();
    	var ajaxurltabs = "<%=request.getContextPath()%>/cris/project/loadTabs.htm";
    	var ajaxurlnavigation = "<%=request.getContextPath()%>/cris/project/loadNavigation.htm";
    	
    	var LoaderSnippet = {    		
    		write : function(text, idelement) {
    			var elem = document.getElementById(idelement);
    			elem.innerHTML = text;		
    		}
    	};

    	var LoaderModal = {        		
       		write : function(text, idelement) {
       			var elem = document.getElementById(idelement);
       			elem.innerHTML = text;		
       		}
        };
    
    	var Loader = {
       		elem : false,
       		write : function(text) {
       			if (!this.elem)
       				this.elem = document.getElementById('logcontent3');
       			this.elem.innerHTML = text;		
      		}
        };
        	
		j(document).ready(function()
				{
				  j("#log3").dialog({closeOnEscape: true, modal: true, autoOpen: false, resizable: false, open: function(event, ui) { j(".ui-dialog-titlebar").hide();}});
			
				  j(".control").click(function()
				  {
					  j(this).toggleClass("expanded");
					  j(this).children("img").toggleClass("hide");
				      j(this).next(".collapsable").slideToggle(300);
				  });
		
		
			
		<c:forEach items="${tabList}" var="tabnavigation">		
		j.ajax( {
			url : ajaxurlnavigation,
			data : {																			
				"tabId" : ${tabnavigation.id},
				"currentOpenedTabId": ${tabId},
				"objectId": ${entity.id},
				"authority": '${authority}'
			},
			success : function(data) {				
				j('#snavmenu-${tabnavigation.shortName}').html(data);				
			},
			error : function(data) {
				//nothing				
			}
		});
		
		
		<c:choose>
		<c:when test="${tabnavigation.id == tabId}">
			j('#cris-tabs-navigation-${tabnavigation.shortName}').show();	
		</c:when>
		<c:otherwise>
		j.ajax( {
			url : ajaxurltabs,
			data : {																			
				"tabId" : ${tabnavigation.id},
				"currentOpenedTabId": ${tabId},
				"objectId": ${entity.id},
				"authority": '${authority}'
			},
			success : function(data) {				
				j('#tb-head2-${tabnavigation.shortName}').html(data);						
				
			},
			error : function(data) {
				//nothing				
			}
		});		
		</c:otherwise>
		</c:choose>
	
		</c:forEach>
		
		});
		-->
	</script>
    
</c:set>

<dspace:layout titlekey="jsp.project.details">

<table align="center" class="miscTable">
<tr>
<td>

<div id="content">
<h1><fmt:message key="jsp.layout.project.detail.title-first" /> ${entity.sourceID}</h1>
<div>&nbsp;</div>
<table align="center" class="miscTable">
	<c:if test="${!entity.status}">
		<div class="warning">
			<fmt:message key="jsp.layout.hku.detail.grant-disabled" />
			<a target="_blank"
				href="<%=request.getContextPath()%>/cris/administrator/project/list.htm?id=${entity.id}&mode=position">
				<fmt:message key="jsp.layout.hku.detail.grant-disabled.fixit" />
			</a>
		</div>
	</c:if>
						
	<tr>

		<td>

		<div id="researcher">
			<jsp:include page="commonDetailsPage.jsp"></jsp:include>
		</div>

		</td>
	</tr>
</table>
</div>
<div id="log3" class="log">
	<img
		src="<%=request.getContextPath()%>/image/cris/bar-loader.gif"
		id="loader3" class="loader"/>
	<div id="logcontent3"></div>
</div>









</td>
</tr>
</table>
</dspace:layout>
