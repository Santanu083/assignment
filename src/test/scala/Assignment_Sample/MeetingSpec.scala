package Assignment_Sample
import cats.effect.IO
import org.http4s._
import org.http4s.implicits._
import org.specs2.matcher.MatchResult
class MeetingSpec extends org.specs2.mutable.Specification {

"Meeting">>{
  "return 200">>{
    uriReturn200()
  }
  "return interviewer">>{
    uriReturnInterviewer()
  }
}
}

privaate[this] val retMeeting:Response[OI]={
  val postMeeting=Request[IO](Method.POSt,uri"/interview")
  val meeting=Meeting.imp[IO]
  Assignment_Routes.meetingRoutes(meeting).orNotFound(postMeeting).unsafeRunSync()

}

private[this]def uriReturn200():MatchResult[Status]=
retMeeting.status must beEqualTo(Status.Ok)
private [this] def uriReturnInterviewer():MatchResult[String]=
retMeeting.as[String].unsafeRunSync() must beEqualTo("{\"message\":\"Interviewer,)
