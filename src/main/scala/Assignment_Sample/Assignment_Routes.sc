package Assignment

import cats.effect.Sync
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
object Assignment_Routes{
  def meetingRoutes [F[_]:Sync](M:Meeting[F]):HttpRequest[F]={
    val dsl=new Http4sDsl[F]{}
    import dsl._
    HttpRequest.of[F]{
      case POST -> Root/"interviewer"/name=>
        for{
          request<-M.interviewer(Meeting.Interviewer(name))
          resp<- ok(request,Header("X-Interviewer","Santanu"),Header("X-Request-Id","value")).headers
        } yield resp
    }
  }
  def interviewerResponseRoutes[F[_]:Sync](I:InterviewerResponse[F]):HttpRoutes[F]={
    val dsl= new Http4sDsl[F]{}
      import dsl._
      HttpRoutes.of[F]{
        case POST->Root/"interviewResponse"=>
        for{
          interviewResponse<-I.post
          resp<-Ok(interviewResponse)
        } yield resp
      }

  }
}