package org.bhargava.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bhargava.messenger.datebase.DataBaseClass;
import org.bhargava.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DataBaseClass.getProfiles();

	public ProfileService() {
		profiles.put("Bhargav Narasipura", new Profile(1, "Bhargav Narasipura", "Bhargav", "Narasipura"));
		profiles.put("Siri Rao", new Profile(2, "Siri Rao", "Siri", "Rao"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
