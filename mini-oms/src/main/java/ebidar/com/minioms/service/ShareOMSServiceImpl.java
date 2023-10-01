package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Dto.ShareDto;
import ebidar.com.minioms.model.Share;
import ebidar.com.minioms.model.enums.SettlementDateType;
import ebidar.com.minioms.repository.ShareRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareOMSServiceImpl implements IShareOMSService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShareOMSServiceImpl.class);

    private final ShareRepository shareRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ShareOMSServiceImpl(ShareRepository shareRepository, ModelMapper modelMapper) {
        this.shareRepository = shareRepository;
        this.modelMapper = modelMapper;
    }

    public boolean validateShare(ShareDto shareDto) {
        if (shareDto == null) {
            return false;
        }
        if (shareDto.getShareCode() == null) {
            return false;
        }

        return true;
    }


    @Override
    public void createShare(ShareDto input) throws NotValidException {
        if (!validateShare(input)) {
            LOGGER.error("createShare : Customer Parameter Not valid ");
            throw new NotValidException("createShare Wrong parameter posted");
        }
        shareRepository.save(modelMapper.map(input, Share.class));
    }

    @Override
    public void deleteShare(String shareCode) throws NotFound {
        var share = shareRepository.findByShareCode(shareCode);

        if (share == null) {
            LOGGER.error("Share Not Found");
            throw new NotFound("Share Not Found");
        }
        shareRepository.delete(share);


    }

    @Override
    public List<Share> getAllShare() {
        return shareRepository.findAll();
    }

    @Override
    public Share getShareById(Long id) throws NotFound {
        var share = shareRepository.findById(id);

        share.orElseThrow(() -> {
            LOGGER.error("Share Not Found");
            return new NotFound("Share Not Found");
        });
        return share.orElse(null);

    }

    @Override
    public Share getShareByCode(String code) throws NotFound {
        var share = shareRepository.findByShareCode(code);
        if (share == null) {
            LOGGER.error("Share Not Found");
            throw new NotFound("Share Not Found");
        }
        return share;
    }

    @Override
    public List<Share> getShareBySettlementDateType(SettlementDateType dateType) throws NotFound {
        return shareRepository.findBySettlementDate(dateType);
    }
}
