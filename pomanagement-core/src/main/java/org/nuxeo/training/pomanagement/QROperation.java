package org.nuxeo.training.pomanagement;

import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;

/**
 *
 */
@Operation(id=QROperation.ID, category="ACME", label="QR Operation", description="Describe here what your operation does.")
public class QROperation {

    public static final String ID = "ACME.QROperation";

    @Context
    protected CoreSession session;
    
    @Context
    protected QRGenService qrgenService;

    @OperationMethod
    public String run(DocumentModel doc) {
    	return qrgenService.buildQRCode(doc);
    }
}
