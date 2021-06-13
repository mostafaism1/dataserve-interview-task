package mostafaism.com.github.dataserveinterviewtask.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mostafaism.com.github.dataserveinterviewtask.validation.PhoneNumber;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must consist of 1 or more alphabetic characters")
    @Column(name = "first_name")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must consist of 1 or more alphabetic characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;

    @Email(message = "Invalid email")
    @Column(name = "email", unique = true)
    private String email;

    @PhoneNumber
    @Column(name = "phone")
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrollment", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

}
