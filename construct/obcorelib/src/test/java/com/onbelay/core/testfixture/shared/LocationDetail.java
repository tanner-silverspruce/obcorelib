/*
 Copyright 2019, OnBelay Consulting Ltd.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.  
 */
package com.onbelay.core.testfixture.shared;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.onbelay.core.codes.annotations.CodeLabelSerializer;
import com.onbelay.core.codes.annotations.InjectCodeLabel;
import com.onbelay.core.entity.snapshot.AbstractDetail;
import com.onbelay.core.enums.CoreErrorCode;
import com.onbelay.core.exception.OBValidationException;
import com.onbelay.core.testfixture.enums.GeoCode;
import org.apache.logging.log4j.core.jackson.ContextDataAsEntryListSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDetail extends AbstractDetail {

	private String name;
	private String geoCodeValue;
	private Integer locationNo;
	private String description; 

	private LocalDate effectiveDate;
	private LocalDateTime generatedDateTime;
	
	public LocationDetail() {
	}
	

	public LocationDetail withName(String name) {
		this.name = name;
		return this;
	}

	public LocationDetail withDescription(String description) {
		this.description = description;
		return this;
	}
	public LocationDetail withGeoCode(GeoCode code) {
		setGeoCode(code);
		return this;
	}


	public void setDefaults() {
		
	}
			

	public void validate() throws OBValidationException {
		if (name == null)
			throw new OBValidationException(CoreErrorCode.MISSING_MY_LOCATION_NAME.getCode());
	}

	@Column(name = "EFFECTIVE_DATE")
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "GENERATED_DATE_TIME")
	public LocalDateTime getGeneratedDateTime() {
		return generatedDateTime;
	}

	public void setGeneratedDateTime(LocalDateTime generatedDateTime) {
		this.generatedDateTime = generatedDateTime;
	}

	@Column(name = "NAME_TXT")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION_TXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "LOCATION_NO")
	public Integer getLocationNo() {
		return locationNo;
	}

	public void setLocationNo(Integer locationNo) {
		this.locationNo = locationNo;
	}

	@Transient
	@JsonIgnore
	public GeoCode getGeoCode() {
		return GeoCode.lookUp(geoCodeValue);
	}

	public void setGeoCode(GeoCode code) {
		this.geoCodeValue = code.getCode();
	}

	@Column(name = "GEO_CODE")
	@InjectCodeLabel(codeFamily = "geoCode", injectedPropertyName = "geoCodeItem")
	@JsonSerialize(using = CodeLabelSerializer.class)
	public String getGeoCodeValue() {
		return geoCodeValue;
	}

	public void setGeoCodeValue(String geoCodeValue) {
		this.geoCodeValue = geoCodeValue;
	}

	public void copyFrom(LocationDetail copy) {
		
		if (copy.name != null)
			this.name = copy.name;


		if (copy.effectiveDate != null)
			this.effectiveDate = copy.effectiveDate;

		if (copy.generatedDateTime != null)
			this.generatedDateTime = copy.generatedDateTime;


		if (copy.geoCodeValue != null)
			this.geoCodeValue = copy.geoCodeValue;

		if (copy.locationNo != null)
			this.locationNo = copy.locationNo;

		if (copy.description != null)
			this.description = copy.description;
	}
	
}
