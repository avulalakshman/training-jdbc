package com.heraizen.hibernatecore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String mobile;

	@OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
	private List<Batch> batches=new ArrayList<Batch>();

	public void addBatch(Batch batch) {
		this.batches.add(batch);
		batch.setTrainer(this);
	}

	public void removeBatch(Batch batch) {
		this.batches.remove(batch);
		batch.setTrainer(null);
	}

}
