package ebidar.com.minioms.controller;



import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Dto.ShareDto;
import ebidar.com.minioms.model.Share;
import ebidar.com.minioms.service.IShareOMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 4200)
@RequestMapping("/api/v1/share/")
public class ShareController {

    private final IShareOMSService shareOMSService ;

    @Autowired
    public ShareController(IShareOMSService shareOMSService) {
        this.shareOMSService = shareOMSService;
    }


    @PostMapping()
    public void createShare(@RequestBody ShareDto input) throws NotValidException {
        shareOMSService.createShare(input);
    }

    @DeleteMapping("")
    public void deleteShare( @RequestParam(required = false) String shareCode) throws NotFound {
        shareOMSService.deleteShare(shareCode);
    }

    @GetMapping("all")
    public List<Share> getAll()  {
        return shareOMSService.getAllShare();
    }

    @GetMapping("{id}")
    public Share getById(@PathVariable Long id) throws NotFound {
        return shareOMSService.getShareById(id);
    }
    @GetMapping("by-code")
    public Share ShareByCode( @RequestParam(required = false) String code) throws NotFound {
        return shareOMSService.getShareByCode(code);
    }

}


