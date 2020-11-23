<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="AddCar">
    <h1> Add Car </h1>
    
  <div class="col-md-8 order-md-1">
      <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddCar">
       
        <div class="mb-3">
          <label for="license_plate">License Plate</label>
          <input type="text" class="form-control" id="license_plate" name="license_plate" placeholder="" value="" required>
          <div class="invalid-feedback">
            Please enter your license plate.
          </div>
        </div>
          
          <div class="mb-3">
          <label for="parking_spot">Parking Spot</label>
          <input type="text" class="form-control" id="parking_spot" name="parking_spot"placeholder="" required>
          <div class="invalid-feedback">
            Please enter your parking spot.
          </div>
        </div>
          
         <div class="mb-3">
            <label for="owner-id">Owner</label>
            <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
              <option value="">Choose...</option>
              <c:forEach var="user" items="${users}" varStatus="status">
                  <option value="${user.id}"> ${user.username}</option>
              </c:forEach>
            </select>
            <div class="invalid-feedback">
              Please select a valid owner.
            </div>
          </div>
          
          <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
      </form><!-- comment -->
        </div>
  </div>
    
 
          
   
    
</t:pageTemplate>
    
    
 <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict'

            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation')

                // Loop over them and prevent submission
                Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
            }, false)
        })()

    </script>