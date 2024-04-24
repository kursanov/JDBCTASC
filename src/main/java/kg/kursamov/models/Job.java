package kg.kursamov.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kg.kursamov.enums.Description;
import kg.kursamov.enums.Position;
import kg.kursamov.enums.Profession;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {

    private Long id;
    @Enumerated(EnumType.STRING)
    private Position positions;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    @Enumerated(EnumType.STRING)
    private Description description;
    private int experience;

    public Job(Position positions, Profession profession, Description description, int experience) {
        this.positions = positions;
        this.profession = profession;
        this.description = description;
        this.experience = experience;
    }

    public Job(Long id, String position, String profession, String description, int experience) {
        this.id = id;
        this.positions = getPositionFromString(position);
        this.profession = getProfessionFromString(profession);
        this.description = getDescriptionFromString(description);
    }

    private Position getPositionFromString(String position) {
        for (Position p : Position.values()) {
            if (p.name().equalsIgnoreCase(position)) {
                return p;
            }
        }
        // Если строка не соответствует ни одной константе перечисления Position, можно вернуть значение по умолчанию или бросить исключение
        return Position.DEFAULT; // например
    }

    private Profession getProfessionFromString(String profession) {
        for (Profession p : Profession.values()) {
            if (p.name().equalsIgnoreCase(profession)) {
                return p;
            }
        }
        return Profession.DEFAULT; // или бросить исключение
    }

    private Description getDescriptionFromString(String description) {
        for (Description d : Description.values()) {
            if (d.name().equalsIgnoreCase(description)) {
                return d;
            }
        }
        return Description.DEFAULT; // или бросить исключение
    }
}

