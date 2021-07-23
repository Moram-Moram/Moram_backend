package radiantMoramMoram.MoramMoram.service.admin;

import radiantMoramMoram.MoramMoram.payload.request.admin.AdminSignInRequest;
import radiantMoramMoram.MoramMoram.payload.response.admin.AdminSignInResponse;

public interface AdminService {

    AdminSignInResponse adminSignIn(AdminSignInRequest adminSignInRequest);

}
