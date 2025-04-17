package com.Apooinments.Appoinments.repositery;

import com.Apooinments.Appoinments.model.Appoinments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppoinmentRepositery extends JpaRepository<Appoinments,Integer> {

    Appoinments findByAppoinmentId (Integer appoinmentId);

}
