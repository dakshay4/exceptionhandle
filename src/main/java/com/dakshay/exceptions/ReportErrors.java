  package com.dakshay.exceptions;

  import lombok.Getter;

  import java.io.Serializable;

  @Getter
  public enum ReportErrors implements MisError, Serializable {

    UNABLE_TO_FETCH_REPORTS(ErrorType.RUNTIME, "UNABLE_TO_FETCH_REPORTS"),
    REPORTING_SERVER_CALL_FAILED(ErrorType.RUNTIME, "REPORTING_SERVER_CALL_FAILED"),
    CONTRACT_SERVER_CALL_FAILED(ErrorType.RUNTIME, "CONTRACT_SERVER_CALL_FAILED"),
    UNABLE_TO_FETCH_FROM_CACHE(ErrorType.RUNTIME, "UNABLE_TO_FETCH_FROM_CACHE"),
    INVALID_REPORT_TYPE(ErrorType.VALIDATION, "INVALID_REPORT_TYPE"),
    OPERATION_NOT_ALLOWED(ErrorType.VALIDATION, "OPERATION_NOT_ALLOWED"),
    VENDOR_AUDIT_NOT_DONE_FOR_BILLING_CYCLE(ErrorType.VALIDATION, "VENDOR_AUDIT_NOT_DONE_FOR_BILLING_CYCLE"),
    NO_PARAMETERS_PROVIDED_FOR_GENERATING_CACHE_KEY(ErrorType.RUNTIME, "No.parameters.provided.for.generating.cache.key"),
    CLIENT_ERROR(ErrorType.RUNTIME, "CLIENT.ERROR"),
    INVALID_DATE_FORMAT(ErrorType.RUNTIME, "Invalid.Date.Format"),
    INVALID_CAB_ID(ErrorType.RUNTIME, "Invalid.Cab.Id"),
    UNABLE_TO_FETCH_ALL_VENDORS(ErrorType.RUNTIME,"unable.to.fetch.all.vendors" ),
    UNABLE_TO_FIND_VENDOR_FROM_VENDOR_NAME(ErrorType.RUNTIME,"Unable.to.find.vendor.from.vendor.name"),
    BUID_NOT_FOUND(ErrorType.VALIDATION, "Business.unit.id.is.not.present"),
    EMP_GUID_NOT_FOUND(ErrorType.VALIDATION, "Emp.guid.is.not.present"),
    UNABLE_TO_FETCH_FROM_CONTRACT(ErrorType.RUNTIME, "Unable.to.fetch.contract"),
    BILLING_CYCLE_NOT_FOUND(ErrorType.RUNTIME, "Billing.cycle.not.found");

    private final ErrorType errorType;
    private final String messageKey;

    ReportErrors(ErrorType errorType, String messageKey) {
      this.errorType = errorType;
      this.messageKey = messageKey;
    }

  }
