<%@ include file="commontags.jsp"%>

<div id="timeoutDialog" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4>
					<spring:message code="session.timeout.header" />
				</h4>
			</div>

			<div class="modal-body">
				<div class="panel-body">
					<div class="form-group row">
						<span class="col-sm-1"></span>
						<span class="col-sm-10">
							<spring:message code="session.timeout.body" />
						</span>
					</div>
					
					<div class="form-group row">
						<span class="col-sm-1"></span>
						<span class="col-sm-10 text-center">
							<button id="keepAliveSession" type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true">
								<spring:message code="button.submit" />						
							</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true">
								<spring:message code="button.close" />							
							</button>							
						</span>
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>