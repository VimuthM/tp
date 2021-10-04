package safeforhall.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import safeforhall.model.AddressBook;
import safeforhall.model.ReadOnlyAddressBook;
import safeforhall.model.resident.Email;
import safeforhall.model.resident.Faculty;
import safeforhall.model.resident.Name;
import safeforhall.model.resident.Phone;
import safeforhall.model.resident.Resident;
import safeforhall.model.resident.Room;
import safeforhall.model.resident.VaccStatus;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Resident[] getSampleResidents() {
        // TODO: Compete lastFetDate and lastCollectionDate
        return new Resident[] {
            new Resident(new Name("Alex Yeoh"), new Room("E417"), new Phone("87438807"), 
                new Email("alexyeoh@example.com"), new VaccStatus("1"), new Faculty("SoC"), null, null),
            new Resident(new Name("Bernice Yu"), new Room("A213"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new VaccStatus("0"), new Faculty("FASS"), null, null),
            new Resident(new Name("Charlotte Oliveiro"), new Room("B423"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new VaccStatus("1"), new Faculty("SoC"), null, null),
            new Resident(new Name("David Li"), new Room("C112"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new VaccStatus("1"), new Faculty("SDE"), null, null),
            new Resident(new Name("Irfan Ibrahim"), new Room("D422"), new Phone("92492021"),
                    new Email("irfan@example.com"), new VaccStatus("1"), new Faculty("FoE"), null, null),
            new Resident(new Name("Roy Balakrishnan"), new Room("A309"), new Phone("92624417"),
                    new Email("royb@example.com"), new VaccStatus("1"), new Faculty("BIZ"), null, null),            
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Resident sampleResident : getSampleResidents()) {
            sampleAb.addResident(sampleResident);
        }
        return sampleAb;
    }

//    /**
//     * Returns a tag set containing the list of strings given.
//     */
//    public static Set<Tag> getTagSet(String... strings) {
//        return Arrays.stream(strings)
//                .map(Tag::new)
//                .collect(Collectors.toSet());
//    }

}
