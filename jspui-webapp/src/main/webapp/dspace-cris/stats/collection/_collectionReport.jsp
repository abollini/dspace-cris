<div id="content-stats">
<c:choose>
<c:when test="${type eq 'item'}">
	<c:set value="2" var="markerasnumber"/>
	<%@include file="../common/topItem.jsp"%>
</c:when>
<c:when test="${type eq 'bitstream'}">
	<c:set value="3" var="markerasnumber"/>
	<%@include file="../common/topBitStream.jsp"%>
</c:when>
<c:otherwise>
	<c:set value="1" var="markerasnumber"/>
	<%@include file="../common/basicReport.jsp"%>	
</c:otherwise>
</c:choose>
</div>
