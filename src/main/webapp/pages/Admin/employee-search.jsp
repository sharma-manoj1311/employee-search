<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<script type="text/javascript">
$(function(){
    $(".datepicker").datepicker(
            { 
                dateFormat: 'yy-mm-dd' 
                
            }        
    );
});
</script>



<section class="main--content" style="background-size: cover !important;
    background-position: 0 0;
    background-image: -webkit-linear-gradient(left, #1492a5 0%, #ff4040 100%) !important;
    background-repeat: repeat-x !important;">
<div class="m-account-w" style="background-size: cover !important;
    background-position: 0 0;
    background-image: -webkit-linear-gradient(left, #1492a5 0%, #ff4040 100%) !important;
    background-repeat: repeat-x !important;">
		<div class="m-account">
			<div class="row no-gutters flex-row-reverse">
				<div class="col-md-12">
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">Employee Search</h3>
						</div>
						<div class="panel-content">

<form:form class="form-horizontal" action="employee-search" modelAttribute="employeeForms" autocomplete="off"   method="post" id="employeeForms">
							
							<div class="form-group row">
								<span class="label-text col-md-2 col-form-label text-md-right">Search Text
								</span>
								<div class="col-md-10">
									<form:input type="text" path="searchText" id="searchText" name="searchText" maxlength="30" class="form-control"/> 
								</div>
								
							</div>
							
							<hr>
							
							<div class="form-group row">
								<span class="label-text col-md-2 col-form-label text-md-right">Start Date</span>
								<div class="col-md-10">
									<form:input type="text" path="startDate" id="startDate"  name="startDate" class="form-control datepicker"/>
								</div>
							</div>
							<hr>
							<div class="form-group row">
								<span class="label-text col-md-2 col-form-label text-md-right">Last Date</span>
								<div class="col-md-10">
									<form:input type="text" path="endDate" id="endDate"  name="endDate" class="form-control datepicker"/>
								</div>
							</div>
							<hr>
							<div class="m-account--actions">
								<button type="submit"
									style="color: #fff !important; background-color: #e16123 !important;"
									class="btn btn-rounded btn-warning">Search Employee</button>
							</div>
</form:form>
						</div>
						<div class="records--list" data-title="Employee List" style="border-top: 65px solid #1991a3;">
			<table id="recordsListView">
				<thead>
					<tr>
						<th>SR No.</th>
						<th>Employee Name</th>
						<th>Job Title</th>
						<th>Age</th>
						<th>Start Date</th>
						<th>End Date</th>
						
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty finalList}">
						<c:forEach items="${finalList}" varStatus="index" var="record">
							<tr>
								<td>${index.count}</td>
								<td>${record.firstName} ${record.lastName}</td>
								<td>${record.jobTittle}</td>
								<td>${record.age}</td>
								
								<td>${record.startDate}</td>
								<td>${record.endDate}</td>
								

							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</section>




