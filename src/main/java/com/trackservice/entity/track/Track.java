package com.trackservice.entity.track;

import com.trackservice.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tracks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalReferenceId;
    private String name;
    private String description;
    private String customerName;
    private String customerEmail;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "track_id")
    private List<TrackLog> trackLogs;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;
    private String status;
}
