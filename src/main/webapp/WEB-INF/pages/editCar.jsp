<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit car">
    <h1> Edit Car </h1>
    
  <div class="col-md-8 order-md-1">
      <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCar">
       
        <div class="mb-3">
          <label for="license_plate">License Plate</label>
          <input type="text" class="form-control" id="license_plate" name="license_plate" placeholder="" required value="${car.licensePlate}">
          <div class="invalid-feedback">
            Please enter your license plate.
          </div>
        </div>
          
          <div class="mb-3">
          <label for="parking_spot">Parking Spot</label>
          <input type="text" class="form-control" id="parking_spot" name="parking_spot" placeholder="" required value="${car.parkingSpot}" >
          <div class="invalid-feedback">
            Please enter your parking spot.
          </div>
        </div>
          
         <div class="mb-3">
            <label for="owner-id">Owner</label>
            <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
              <option value="">Choose...</option>
              <c:forEach var="user" items="${users}" varStatus="status">
                  <option value="${user.id}" ${car.username eq user.username ? 'selected' : ''} > ${user.username}</option>
              </c:forEach>
            </select>
            <div class="invalid-feedback">
              Please select a valid owner.
            </div>
          </div>
          <input type="hidden" name="car_id" value="${car.id}" />
          <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
      </form><!-- comment -->
        </div>
  </div>
    
  <script src="form-validation.js"></script>
          
   
    
</t:pageTemplate>
    
    
 