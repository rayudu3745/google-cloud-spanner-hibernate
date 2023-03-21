/*
 * Copyright 2019-2023 Google LLC
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 */

package com.google.cloud.spanner.hibernate.entities;

import com.google.cloud.spanner.hibernate.BitReversedSequenceStyleGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

/** Test entity using a bit-reversed sequence generator for ID generation. */
@Entity
public class Singer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerId")
  @GenericGenerator(
             name = "customerId",
             strategy = "com.google.cloud.spanner.hibernate.BitReversedSequenceStyleGenerator",
             parameters = {
                 @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "singerId"),
                 @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1000"),
                 @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "50000"),
                 @Parameter(name = BitReversedSequenceStyleGenerator.EXCLUDE_RANGES_PARAM, 
                     value = "[1,1000] [10000,20000]"),
             })
  @Column(nullable = false)
  private long id;
  
  public Singer() {}
  
  public long getId() {
    return id;
  }

}
