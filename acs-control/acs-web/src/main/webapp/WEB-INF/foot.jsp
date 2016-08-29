<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<!-- Footer starts -->
		<footer>
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<!-- Copyright info -->
						<p class="copy">Copyright &copy; 2016 </p>
					</div>
				</div>
			</div>
		</footer>

		<!-- Footer ends -->

		<!-- Scroll to top -->
		<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>

		<!-- JS -->
		<script src="<%=basePath %>resources/js/jquery.js"></script>
		<!-- jQuery -->
		<script src="<%=basePath %>resources/js/bootstrap.js"></script>
		<!-- Bootstrap -->
		<script src="<%=basePath %>resources/js/jquery-ui-1.9.2.custom.min.js"></script>
		<!-- jQuery UI -->
		<script src="<%=basePath %>resources/js/fullcalendar.min.js"></script>
		<!-- Full Google Calendar - Calendar -->
		<script src="<%=basePath %>resources/js/jquery.rateit.min.js"></script>
		<!-- RateIt - Star rating -->
		<script src="<%=basePath %>resources/js/jquery.prettyPhoto.js"></script>
		<!-- prettyPhoto -->

		<!-- jQuery Flot -->
		<script src="<%=basePath %>resources/js/excanvas.min.js"></script>
		<script src="<%=basePath %>resources/js/jquery.flot.js"></script>
		<script src="<%=basePath %>resources/js/jquery.flot.resize.js"></script>
		<script src="<%=basePath %>resources/js/jquery.flot.pie.js"></script>
		<script src="<%=basePath %>resources/js/jquery.flot.stack.js"></script>

		<!-- jQuery Notification - Noty -->
<%-- 		<script src="<%=basePath %>resources/js/jquery.noty.js"></script> --%>
		<!-- jQuery Notify -->
<%-- 		<script src="<%=basePath %>resources/js/themes/default.js"></script> --%>
		<!-- jQuery Notify -->
<%-- 		<script src="<%=basePath %>resources/js/layouts/bottom.js"></script> --%>
		<!-- jQuery Notify -->
<%-- 		<script src="<%=basePath %>resources/js/layouts/topRight.js"></script> --%>
		<!-- jQuery Notify -->
<%-- 		<script src="<%=basePath %>resources/js/layouts/top.js"></script> --%>
		<!-- jQuery Notify -->
		<!-- jQuery Notification ends -->
		<script src="<%=basePath %>resources/js/sparklines.js"></script>
		<!-- Sparklines -->
		<script src="<%=basePath %>resources/js/jquery.cleditor.min.js"></script>
		<!-- CLEditor -->
		<script src="<%=basePath %>resources/js/bootstrap-datetimepicker.min.js"></script>
		<!-- Date picker -->
		<script src="<%=basePath %>resources/js/filter.js"></script>
		<!-- Filter for support page -->
		<script src="<%=basePath %>resources/js/custom.js"></script>
		<!-- Custom codes -->
		<script src="<%=basePath %>resources/js/charts.js"></script>
		<!-- Charts & Graphs -->
		<!-- Script for this page -->
		<script type="text/javascript" src="<%=basePath %>resources/js/jquery.i18n.properties-min-1.0.9.js"></script>
		<script type="text/javascript" src="<%=basePath %>resources/js/message.js"></script>
		<script src="<%=basePath %>resources/js/bootstrap-modalmanager.js"></script>
    	<script src="<%=basePath %>resources/js/bootstrap-modal.js"></script>
    	<script src="<%=basePath %>resources/js/bootstrap-select.min.js"></script>
    	<script src="<%=basePath %>resources/js/bootbox.js"></script>

	</body>

</html>