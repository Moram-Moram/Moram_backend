package radiantMoramMoram.MoramMoram.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.admin.Admin;
import radiantMoramMoram.MoramMoram.payload.request.admin.AdminSignInRequest;
import radiantMoramMoram.MoramMoram.payload.response.admin.AdminSignInResponse;
import radiantMoramMoram.MoramMoram.repository.admin.AdminRepositpry;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepositpry adminRepositpry;


    @Override
    public AdminSignInResponse adminSignIn(AdminSignInRequest adminSignInRequest) {

        return null;
    }
}
