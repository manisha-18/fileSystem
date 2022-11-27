package topContent;

import javax.naming.ldap.Control;

public interface TopContentService {

    int incrementPopularity(int contentId, int popularity);
    int decrementPopularity(int contentId);

}
