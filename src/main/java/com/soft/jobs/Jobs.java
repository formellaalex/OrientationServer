package com.soft.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.soft.entity.Orientation;
import com.soft.entity.OrientationAvg;
import com.soft.service.DbOperations;

public class Jobs {

	@Autowired
	private DbOperations dbOperations;

	private String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

    @Scheduled(fixedRate = 30000)
    public void reportOrientationsAvg() {
        List<Orientation> list = dbOperations.getOrientations();
        int avg = 0;
        for(Orientation o: list) {
        	avg += o.getOrientation();
        }

        if (!list.isEmpty()) {
            avg /= list.size();
        }

        OrientationAvg orientationAvg = new OrientationAvg();

        orientationAvg.setDateTime(getDateTime());
        orientationAvg.setAvg(avg);

        dbOperations.add(orientationAvg);
    }
}
