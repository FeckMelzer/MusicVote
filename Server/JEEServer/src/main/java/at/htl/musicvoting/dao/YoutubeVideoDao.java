package at.htl.musicvoting.dao;

import at.htl.musicvoting.model.AvailabilityStatus;
import at.htl.musicvoting.model.YoutubeVideo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class YoutubeVideoDao {
    @PersistenceContext
    EntityManager em;


    public void persist(YoutubeVideo newVideo) {
        em.persist(newVideo);
    }

    public boolean existsInDatabase(String videoId) {
        TypedQuery query = em.createNamedQuery("YoutubeVideo.getByVideoId", YoutubeVideo.class).setParameter("VIDEOID", videoId);
        return !query.getResultList().isEmpty();
    }

    public void updateToDownloaded(YoutubeVideo newVideo, int exitCode) {
        if(exitCode == 0)
            newVideo.setStatus(AvailabilityStatus.AVAILABLE);
        else
            newVideo.setStatus(AvailabilityStatus.NOT_AVALABLE);
        em.merge(newVideo);
    }
}