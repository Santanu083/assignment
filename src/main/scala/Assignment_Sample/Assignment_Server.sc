package Assignment

import java.lang.System.Logger

import Assignment_Sample.{InterviewerResponse, Meeting}
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.Logger

import scala.concurrent.ExecutionContext.global

object Assignment_Server{
  def stream[F[_]:ConcurrentEffect](Implicit: T:Timer[F],C: ContextShift[F]):Stream[F,Nothing]={
    for {
      client<-BlazeClientBuilder[F](global).stream
      meetingAlg=Meeting.impl[F]
     interviewerResponseAlg =InterviewerResponse.send[F](client)
      httpApp=(
        Assignment_Routes.meetingRoutes[F](meetingAlg)<+>
        Assignment_Routes.meetingRoutes[F](interviewerResponseAlg)
      ).orNotFound
      finalHttpApp= Logger.httpApp(true,true)(httpApp)
      exitCode<-BlazeServerBuilder[F](global)
          .bindHttp(8080,"0.0.0.0")
          .withHttpApp(finalHttpApp)
          .serve
    }yield exitCode
  }.drain
}