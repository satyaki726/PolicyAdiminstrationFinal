<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Policy Administration System</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Pinyon+Script&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/style-table.css">
<link rel="stylesheet" href="/css/style-admin.css">
</head>
<body>
	<div class="main-container-register">
		<div class="section grid">
			<%@ include file="navbar.jsp"%>
			<div class="content list-container">
				<h1>Find In-patient Services</h1>
				<div class="container">
					<form:form action="addSpecialist" method="POST"
						modelAttribute="specialistDetails">
						<div class="form-group">
							<form:label path="name">Enter Consumer's Name:</form:label>
							<form:input path="name" class="form-control" id="name"
								required="required" />
						</div>
						<div class="form-group">
							<form:label path="dob">Enter DOB:</form:label>
							<form:select path="dob" class="form-control" id="dob"
								required="required" />
						</div>
						<div class="form-group">
							<form:label path="pandetails">Enter the Pan Details:</form:label>
							<form:input path="pandetails" class="form-control"
								id="pandetails" required="required" />
						</div>
						<div class="form-group">
							<form:label path="email">Enter the Email:</form:label>
							<form:input path="email" class="form-control" id="email"
								required="required" />
						</div>
						<div class="form-group">
							<form:label path="phone">Enter the Phone No:</form:label>
							<form:input path="phone" class="form-control" id="phone"
								required="required" />
						</div>
						<div class="form-group">
							<form:label path="agentname">Enter the Agent Name:</form:label>
							<form:input path="agentname" class="form-control" id="agentname"
								required="required" />
						</div>
						<div class="form-group">
							<form:label path="agentid">Enter the Agent ID:</form:label>
							<form:input path="agentid" class="form-control" id="agentid"
								required="required" />
						</div>
						<c:forEach items="${consumerDetails.business}" var="business">
							<table>
								<tr>
									<th>Name</th>
									<td><input type="text" value="${business.name}" /></td>
								</tr>
								<tr>
									<th>Business Category</th>
									<td><input type="text"
										value="${business.businesscategory}" /></td>
								</tr>
								<tr>
									<th>Business Type</th>
									<td><input type="text" value="${business.businesstype}" /></td>
								</tr>
								<tr>
									<th>Business Turnover</th>
									<td><input type="text"
										value="${business.businessturnover}" /></td>
								</tr>
								<tr>
									<th>Capital Invested</th>
									<td><input type="text" value="${business.capitalinvested}" /></td>
								</tr>
								<tr>
									<th>Total Employees</th>
									<td><input type="text" value="${business.totalemployees}" /></td>
								</tr>
								<tr>
									<th>Business Value</th>
									<td><input type="text" value="${business.businessvalue}" /></td>
								</tr>
								<tr>
									<th>Business Age</th>
									<td><input type="text" value="${business.businessage}" /></td>
								</tr>
								<c:forEach items="${business.property}" var="property">
									<table>
										<tr>
											<th>Property Type</th>
											<td><input type="text" value="${property.propertytype}" /></td>
										</tr>
										<tr>
											<th>Building_Sqft</th>
											<td><input type="text" value="${property.buildingsqft}" /></td>
										</tr>
										<tr>
											<th>Building_Type</th>
											<td><input type="text" value="${property.buildingtype}" /></td>
										</tr>
										<tr>
											<th>Building_Storeys</th>
											<td><input type="text" value="${property.buildingsqft}" /></td>
										</tr>
										<tr>
											<th>Building_Age</th>
											<td><input type="text" value="${property.buildingage}" /></td>
										</tr>
										<tr>
											<th>Property_Value</th>
											<td><input type="text" value="${property.propertyvalue}" /></td>
										</tr>
										<tr>
											<th>Cost_of_the_asset</th>
											<td><input type="text" value="${property.costoftheasset}" /></td>
										</tr>
										<tr>
											<th>Useful_Life_of_the_Asset</th>
											<td><input type="text" value="${property.usefullifeoftheAsset}" /></td>
										</tr>
										<tr>
											<th>Salvage_value</th>
											<td><input type="text" value="${property.salvagevalue}" /></td>
										</tr>
									</table>
								</c:forEach>
							</table>
							<hr />
						</c:forEach>
						<form:button class="btn">Create</form:button>
					</form:form>
					<h1>${message}</h1>
				</div>
			</div>
		</div>
	</div>
</body>
</html>