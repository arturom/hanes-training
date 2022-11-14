package org.nuxeo.training.pomanagement;

import org.apache.commons.lang3.StringUtils;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.platform.usermanager.UserManager;

/**
 *
 */
@Operation(id=CheckUserPermission.ID, category="ACME", label="Check User Permission", description="Checks the user permission")
public class CheckUserPermission {

    public static final String ID = "ACME.CheckUserPermission";

    @Context
    protected CoreSession session;
    
    @Context
    protected OperationContext ctx;

    @Context
    protected UserManager userManager;

    @Param(name = "username", required = true)
    protected String username;

    @Param(name = "permission", required = true)
    protected String permission;

    @Param(name = "docID", required = true)
    protected String docID;

    @Param(name = "varName", required = false)
    protected String varName;

    @OperationMethod
    public boolean run() {
    	NuxeoPrincipal user = userManager.getPrincipal(username);
    	boolean hasPermission = session.hasPermission(user, new IdRef(docID), permission);
    	if (varName != null) {
    		ctx.put(varName, hasPermission);
    	}
    	return hasPermission;
    }
}
