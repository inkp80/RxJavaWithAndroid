package inkyu.naver.com.rxjavaforandroid;

/**
 * Created by inkyu.park on 2018. 6. 27..
 */

public class GithubRepo {
	public int id;
	public String name;
	public String htmlUrl;
	public String description;
	public String language;
	public int stargazersCount;

	public GithubRepo(int id, String name, String htmlUrl, String description, String language, int stargazersCount) {
		this.id = id;
		this.name = name;
		this.htmlUrl = htmlUrl;
		this.description = description;
		this.language = language;
		this.stargazersCount = stargazersCount;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "" + id + ", " + name + ", " + htmlUrl + ", " + description + ", " + language + ", " + stargazersCount;
	}
}
