package com.intelehealth.api;

import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIServices {
	private static final String VISIT_PUSH_ENDPOINT = "https://pathqa.intelehealth.org/EMR-Middleware/webapi/push/pushdata";
	private static final String START_VISIT_ENDPOINT = "https://pathqa.intelehealth.org/openmrs/ws/rest/v1/encounter";
	private static final String SIGN_AND_SUBMIT_ENDPOINT = "https://pathqa.intelehealth.org/openmrs/ws/rest/v1/encounter";
	private static final String SEARCH_PATIENT_ENDPOINT = "https://pathqa.intelehealth.org/openmrs/ws/rest/v1/patient?q=%s&v=custom:(uuid,identifiers:(identifierType:(name),identifier),person)";

	public static String createVisitUsingRestAssured(RequestSpecification request) {
		Response response = request.body(PayloadGenerator.createVisitUsingRestAssured()).post(VISIT_PUSH_ENDPOINT);
		System.out.println("======================================================================================================"+response.jsonPath().getString("data.patientlist[0].openmrs_id"));
		return response.jsonPath().getString("data.patientlist[0].openmrs_id");

	}

	public static void startVisitUsingRestAssured(RequestSpecification request) {
		request.body(PayloadGenerator.startVisitNote()).post(START_VISIT_ENDPOINT);
	}

	public static void signAndSubmitUsingRestAssured(RequestSpecification request) {
		request.body(PayloadGenerator.signAndSubmit()).post(SIGN_AND_SUBMIT_ENDPOINT);
	}

	public static String createPriorityVisitUsingRestAssured(RequestSpecification request) {
		Response response = request.body(PayloadGenerator.createPriorityVisitUsingRestAssured()).post(VISIT_PUSH_ENDPOINT);
		System.out.println(response.asPrettyString());
		System.out.println("======================================================================================================"+response.jsonPath().getString("data.patientlist[0].openmrs_id"));
		return response.jsonPath().getString("data.patientlist[0].openmrs_id");

	}
	public static boolean createAppointmentUsingRestAssured(RequestSpecification request) {

		// Step 1: Create visit → extract openMrsId and patientId
		Response visitResponse = request.body(PayloadGenerator.createVisitUsingRestAssured()).post(VISIT_PUSH_ENDPOINT);

//		System.out.println(visitResponse.asPrettyString());

		String openMrsId = visitResponse.jsonPath().getString("data.patientlist[0].openmrs_id");

		String patientId = visitResponse.jsonPath().getString("data.patientlist[0].uuid");
		String visitUuid = visitResponse.jsonPath().getString("data.visitlist[0].uuid");
		System.out.println("[APIServices] openMrsId : " + openMrsId);
		System.out.println("[APIServices] patientId : " + patientId);

		// Step 2: Build appointment payload with real values
		Map<String, Object> payload = PayloadGenerator.createAppointmentPayload(openMrsId, patientId, visitUuid);
		// ── If no slot was available, signal caller to skip ───────────────────
		if (payload == null) {
			System.out.println(
					"[APIServices] ⚠️ Appointment payload is null " + "— no slots available, tests will be skipped");
			return false; // ← false = skip appointment tests
		}
		// System.out.println("[APIServices] Appointment payload → "
		// + new GsonBuilder().setPrettyPrinting().create().toJson(payload));

		// Step 3: Push appointment
		Response response = request.body(payload).post(VISIT_PUSH_ENDPOINT);

		//System.out.println("[APIServices] Appointment response → " + response.asPrettyString());

		response.then().statusCode(200);
		return true;
	}
	public static void searchPatient(RequestSpecification request, String openMRSID) {
		request.get(String.format(SEARCH_PATIENT_ENDPOINT, openMRSID)).then().assertThat().body("results[0].uuid",
				notNullValue());
		;
	}
}
