package org.nuxeo.training.pomanagement;

import org.nuxeo.ecm.core.api.DocumentModel;

public interface QRGenService {
    /** Add some methods here. **/
	
	public String buildQRCode(DocumentModel doc);
}
